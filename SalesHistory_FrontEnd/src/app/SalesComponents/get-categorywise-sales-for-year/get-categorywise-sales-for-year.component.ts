import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { SalesService } from 'src/app/services/sales.service';

@Component({
  selector: 'app-get-categorywise-sales-for-year',
  templateUrl: './get-categorywise-sales-for-year.component.html',
  styleUrls: ['./get-categorywise-sales-for-year.component.css']
})
export class GetCategorywiseSalesForYearComponent implements OnInit{
  errMsg: string ='';
  err: boolean=true;
  ngOnInit(): void {
    
  }

  constructor(private service:SalesService){}

  quantity:any[]=[]
  amountSold:any[]=[]
  qYear!:number
  aYear!:number

  //gettin quantity for category
  public getQuantityCategorywise(){
    this.service.getQuantityCategorywiseForYear(this.qYear).subscribe((response:any)=>{
      this.quantity=response
      this.err=true
    },(error:HttpErrorResponse)=>{
      alert(error.error.errorMessage);
      this.errMsg=error.error.errorMessage;
      this.err=false;
      this.quantity=[]
    })
  }

  //getting amount sold for category
  public getAmountSoldCategorywise(){
    this.service.getAmountSoldCategorywiseForYear(this.aYear).subscribe((response:any)=>{
      this.amountSold=response
      this.err=true
    },(error:HttpErrorResponse)=>{
      alert(error.error.errorMessage);
      this.errMsg=error.error.errorMessage;
      this.err=false;
      this.amountSold=[]
    })
  }
}
