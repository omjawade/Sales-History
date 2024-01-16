import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerService } from 'src/app/services/customer.service';
import { Customer } from '../Customer';

@Component({
  selector: 'app-showcustomer',
  templateUrl: './showcustomer.component.html',
  styleUrls: ['./showcustomer.component.css']
})
export class ShowcustomerComponent implements OnInit {
  constructor(private customerService: CustomerService, private router: Router) { }
  public searchParam: string = 'all';
  public searchQuery: string = '';
  ngOnInit(): void {
    this.customerService.findAll().subscribe((response: any) => {
      this.customers = response;
    });
  }

  ishidden:boolean =true;

  changeHidden(){
    if (this.searchParam==="searchBetweenCreditLimit") {
      this.ishidden=false 
    }else{
      this.ishidden=true
    }
  }

  public customers: Customer[] = [];

  //search logic
  public search() {
    switch (this.searchParam) {
      case 'all':
        this.customerService.findAll().subscribe((responce: any) => {
          this.customers = responce;
        },(error:HttpErrorResponse)=>{
          alert(error.error.errorMessage);
          this.errMsg=error.error.errorMessage;
          this.err=false;
        });
        break;
      case 'firstname':
        this.customerService.findByFirstName(this.searchQuery)
          .subscribe((response: any) => {
            this.customers = response;
        },(error:HttpErrorResponse)=>{
          alert(error.error.errorMessage);
          this.errMsg=error.error.errorMessage;
          this.err=false;
        });
        break;
        case 'city':
          this.customerService.findByCity(this.searchQuery).subscribe((response: any) => {
            this.customers = response;
          },(error:HttpErrorResponse)=>{
            alert(error.error.errorMessage);
            this.errMsg=error.error.errorMessage;
            this.err=false;
          });
          break;
        case 'income':
          this.customerService.findByIncome(this.searchQuery).subscribe((responce: any) => {
            this.customers = responce;
          },(error:HttpErrorResponse)=>{
            alert(error.error.errorMessage);
            this.errMsg=error.error.errorMessage;
            this.err=false;
          });
          break;
        case 'searchBetweenCreditLimit':
          this.searchBetweenCreditLimit();
          break;
    }
  }

  public searchBetweenCreditLimit(){
    let params = this.searchQuery.split("-")
    let paramsInt = params.map((x)=>parseInt(x));
    let beginLimit= paramsInt[0];
    let endLimit = paramsInt[1];
    this.customerService.getBetweenCreditLimit(beginLimit,endLimit).subscribe((response:any)=>{
      this.customers = response;
    },(error:HttpErrorResponse)=>{
      alert(error.error.errorMessage);
      this.errMsg=error.error.errorMessage;
      this.err=false;
    })
  }

  errMsg:string=''
  err:boolean=true;

  currentPage:number=1;
rowsPerPage:number=7;

//pagination logic
getCurrentPageCustomer(): Customer[] {
  const startIndex = (this.currentPage - 1) * this.rowsPerPage;
  const endIndex = startIndex + this.rowsPerPage;
  return this.customers.slice(startIndex, endIndex);
}
getTotalPages(): number {
  return Math.ceil(this.customers.length / this.rowsPerPage);
}

// Function to go to a specific page
goToPage(pageNumber: number): void {
  const totalPages = Math.ceil(this.customers.length / this.rowsPerPage);
  if (pageNumber >= 1 && pageNumber <= totalPages) {
    this.currentPage = pageNumber;
  }
}

}

