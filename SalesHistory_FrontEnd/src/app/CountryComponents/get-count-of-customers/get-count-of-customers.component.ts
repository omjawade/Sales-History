import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { CountryService } from 'src/app/services/country.service';

@Component({
  selector: 'app-get-count-of-customers',
  templateUrl: './get-count-of-customers.component.html',
  styleUrls: ['./get-count-of-customers.component.css']
})
export class GetCountOfCustomersComponent implements OnInit {
  ngOnInit(): void {
    this.service.getCountriwiseCustomerCount().subscribe((response: any) => {
      this.countries = response;
    })
  }

  constructor(private service: CountryService) { }

  //variable declaration
  countries: any[] = []
  regions: any[] = []

  searchQuery: string = ''

  errMsg: string = ''
  err: boolean = false
  //search logic
  public search() {
    this.service.getRegionwiseCustomerCount(this.searchQuery).subscribe((response: any) => {
      this.err = false;
      this.regions = response;
    }, (error: HttpErrorResponse) => {
      this.errMsg = error.error.errorMessage;
      this.err = true;
    }
    )
  }

  //pagination logic
  currentPage: number = 1;
  rowsPerPage: number = 7;

  getCurrentPageCountries(): any[] {
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
