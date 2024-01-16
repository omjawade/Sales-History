import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Product } from '../product';
import { ProductService } from '../../services/productservices.service';
import { HttpErrorResponse } from '@angular/common/http';


@Component({
  selector: 'app-showproduct',
  templateUrl: './showproduct.component.html',
  styleUrls: ['./showproduct.component.css']
})
export class ShowproductComponent implements OnInit {
  //variable declaration
  errMsg: string = '';
  err: boolean = true;
  constructor(private service: ProductService, private router: Router) { }
  public searchParam: string = 'all';
  public searchQuery: string = '';
  public sortParam: string = 'prodId';
  ngOnInit(): void {
    this.service.findAll().subscribe((response: any) => {
      this.prod = response;
    })
  }

  public prod: Product[] = []

  //searching logic
  public search() {
    switch (this.searchParam) {
      case 'all':
        this.service.findAll().subscribe((response: any) => {
          this.prod = response;
          this.err=true
        },(error:HttpErrorResponse)=>{
          this.prod=[]
          alert(error.error.errorMessage)
          this.errMsg = error.error.errorMessage;
          this.err = false
        })
        break;
      case 'category':
        this.service.getByCategory(this.searchQuery).subscribe((response: any) => {
          this.prod = response;
          this.err=true
        },(error:HttpErrorResponse)=>{
          this.prod=[]
          alert(error.error.errorMessage)
          this.errMsg = error.error.errorMessage;
          this.err = false
        })
        break;
      case 'status':
        this.service.getByStatus(this.searchQuery).subscribe((response: any) => {
          console.log(response);
          
          this.prod = response;
          this.err=true
        },(error:HttpErrorResponse)=>{
          this.prod=[]
          alert(error.error.errorMessage)
          this.errMsg = error.error.errorMessage;
          this.err = false
        })
        break;
      case 'subcategory':
        this.service.getBySubcategory(this.searchQuery).subscribe((response: any) => {
          this.prod = response;
          this.err=true
        },(error:HttpErrorResponse)=>{
          this.prod=[]
          alert(error.error.errorMessage)
          this.errMsg = error.error.errorMessage;
          this.err = false
        })
        break;
      case 'supplier':
        this.service.getBySupplier(this.searchQuery).subscribe((response: any) => {
          this.prod = response;
          this.err=true
        },(error:HttpErrorResponse)=>{
          this.prod=[]
          alert(error.error.errorMessage)
          this.errMsg = error.error.errorMessage;
          this.err = false
        })
        break;
      case 'duplicate':
        this.router.navigate(['/duplicate']);
        break;
      case 'sold':
        this.service.getSoldProducts().subscribe((response: any) => {
          console.log(response);
          
          this.prod = response;
          this.err=true
        },(error:HttpErrorResponse)=>{
          this.prod=[]
          alert(error.error.errorMessage)
          this.errMsg = error.error.errorMessage;
          this.err = false
        })
        break;
      case 'soldTo':
        let custId: number = Number(this.searchQuery)
        this.service.getProductsSoldToCustomer(custId).subscribe((response: any) => {
          this.prod = response;
          this.err=true
        },(error:HttpErrorResponse)=>{
          this.prod=[]
          alert(error.error.errorMessage)
          this.errMsg = error.error.errorMessage;
          this.err = false
        })
        break;
      case 'byChannel':
        this.service.getProductsSoldByChannel(this.searchQuery).subscribe((response:any)=>{
          this.prod = response;
          this.err=true
        },(error:HttpErrorResponse)=>{
          this.prod=[]
          alert(error.error.errorMessage)
          this.errMsg = error.error.errorMessage;
          this.err = false
        })
        break;
      default:
        break;
    }
  }

  //sorting logic
  public sort() {
    this.service.sortBy(this.sortParam).subscribe((response: any) => {
      this.prod = response;
      this.err=true
    },(error:HttpErrorResponse)=>{
      this.prod=[]
      alert(error.error.errorMessage)
      this.errMsg = error.error.errorMessage;
      this.err = false
    })
  }

  //pagination logic
  currentPage: number = 1;
  rowsPerPage: number = 7;

  getCurrentPageProducts(): Product[] {
    const startIndex = (this.currentPage - 1) * this.rowsPerPage;
    const endIndex = startIndex + this.rowsPerPage;
    return this.prod.slice(startIndex, endIndex);
  }
  getTotalPages(): number {
    return Math.ceil(this.prod.length / this.rowsPerPage);
  }

  // Function to go to a specific page
  goToPage(pageNumber: number): void {
    const totalPages = Math.ceil(this.prod.length / this.rowsPerPage);
    if (pageNumber >= 1 && pageNumber <= totalPages) {
      this.currentPage = pageNumber;
    }
  }

}
