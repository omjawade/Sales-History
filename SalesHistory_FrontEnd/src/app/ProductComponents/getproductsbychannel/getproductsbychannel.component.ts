import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/services/productservices.service';
import { Product } from '../product';

@Component({
  selector: 'app-getproductsbychannel',
  templateUrl: './getproductsbychannel.component.html',
  styleUrls: ['./getproductsbychannel.component.css']
})
export class GetproductsbychannelComponent implements OnInit {

  prod:any[]=[];
  errMsg:string='';
  err: boolean=true;

  constructor(private service : ProductService){}
  
  ngOnInit(): void {
    this.getByChannel();
  }

  channelName:string ='Retail'

  public getByChannel(){
    this.service.getByChannel(this.channelName).subscribe((response:any)=>{
      this.currentPage = 1;
      this.prod=response;
    },(error:HttpErrorResponse)=>{
      this.prod=[];
      this.errMsg = error.error.errorMessage;
      this.err = false
      console.log(this.errMsg);
      alert(this.errMsg);
    })
  }

  currentPage: number = 1;
  rowsPerPage: number = 7;

  getCurrentPage(): any[] {
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
