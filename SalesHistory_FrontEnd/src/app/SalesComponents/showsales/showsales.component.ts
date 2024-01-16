import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SalesService } from 'src/app/services/sales.service';
import { Sales } from '../Sales';


@Component({
  selector: 'app-showsales',
  templateUrl: './showsales.component.html',
  styleUrls: ['./showsales.component.css']
})
export class ShowsalesComponent implements OnInit {
  errMsg: string = '';
  err: boolean = true;

  constructor(private service: SalesService, private router: Router) { }

  ngOnInit(): void {
    this.search()
  }
  sales: Sales[] = []

  searchbox: string = 'all'
  searchParam!: string

  //searching logic
  public search() {
    switch (this.searchbox) {
      case 'all':
        this.service.getAll().subscribe((response: any) => {
          this.sales = response;
          this.err = true;
        }, (error: HttpErrorResponse) => {
          this.sales = []
          alert(error.error.errorMessage);
          this.errMsg = error.error.errorMessage;
          this.err = false;
        })
        break;
      case 'byQuarter':
        this.service.getByQuarter(this.searchParam).subscribe((response: any) => {
          this.sales = response;
          this.err=true
        }, (error: HttpErrorResponse) => {
          this.sales = []
          alert(error.error.errorMessage);
          this.errMsg = error.error.errorMessage;
          this.err = false;
        })
        break;
      case 'byDate':
        this.service.getByDate(this.searchParam).subscribe((response: any) => {
          this.sales = response;
          this.err=true
        }, (error: HttpErrorResponse) => {
          this.sales = []
          alert(error.error.errorMessage);
          this.errMsg = error.error.errorMessage;
          this.err = false;
        })
        break;
      default:
        break;
    }
  }

  //pagination logic
  currentPage: number = 1;
  rowsPerPage: number = 7;

  getCurrentPage(): Sales[] {
    const startIndex = (this.currentPage - 1) * this.rowsPerPage;
    const endIndex = startIndex + this.rowsPerPage;
    return this.sales.slice(startIndex, endIndex);
  }
  getTotalPages(): number {
    return Math.ceil(this.sales.length / this.rowsPerPage);
  }

  // Function to go to a specific page
  goToPage(pageNumber: number): void {
    const totalPages = Math.ceil(this.sales.length / this.rowsPerPage);
    if (pageNumber >= 1 && pageNumber <= totalPages) {
      this.currentPage = pageNumber;
    }
  }
}
