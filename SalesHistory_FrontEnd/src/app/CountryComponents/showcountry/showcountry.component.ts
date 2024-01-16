import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CountryService } from 'src/app/services/country.service';
import { Country } from '../Country';

@Component({
  selector: 'app-showcountry',
  templateUrl: './showcountry.component.html',
  styleUrls: ['./showcountry.component.css']
})
export class ShowcountryComponent implements OnInit {
  //variable declaration
  byId: number = 0;
  errMsg: string = '';
  err: boolean = false;
  //Autowiring using constructor
  constructor(private countryService: CountryService, private router: Router) { }
  ngOnInit(): void {

    this.countryService.findAll().subscribe((response: any) => {
      this.countries = response;
    }, (error: HttpErrorResponse) => {
      this.errMsg = error.error.errorMessage;
      this.err = false;
    })
  }
  countries: Country[] = []
  //search logic
  public search() {
    if (this.byId === null) {
      this.ngOnInit()
    }
    this.countryService.getById(this.byId).subscribe((response: any) => {
      let country: Country = response;
      this.countries = []
      this.err = true
      this.countries.push(response)
    }, (error: HttpErrorResponse) => {
      this.errMsg = error.error.errorMessage;
      this.err = false;
    })
  }

  //pagination logic
  currentPage: number = 1;
  rowsPerPage: number = 6;

  // Function to get the countries for the current page
  getCurrentPageCountries(): Country[] {
    const startIndex = (this.currentPage - 1) * this.rowsPerPage;
    const endIndex = startIndex + this.rowsPerPage;
    return this.countries.slice(startIndex, endIndex);
  }
  getTotalPages(): number {
    return Math.ceil(this.countries.length / this.rowsPerPage);
  }

  // Function to go to a specific page
  goToPage(pageNumber: number): void {
    const totalPages = Math.ceil(this.countries.length / this.rowsPerPage);
    if (pageNumber >= 1 && pageNumber <= totalPages) {
      this.currentPage = pageNumber;
    }
  }
}

