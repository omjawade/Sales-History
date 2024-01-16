import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { SalesService } from 'src/app/services/sales.service';

@Component({
  selector: 'app-get-categorywise-sales',
  templateUrl: './get-categorywise-sales.component.html',
  styleUrls: ['./get-categorywise-sales.component.css']
})
export class GetCategorywiseSalesComponent implements OnInit {
  errMsg: string = '';
  err: boolean = true;
  ngOnInit(): void {
    this.getQuantityCategorywise()
    this.getAmountSoldCategorywise()
  }

  constructor(private service:SalesService){}

  quantity:any[]=[]
  amountSold:any[]=[]

  public getQuantityCategorywise(){
    this.service.getQuantityCategorywise().subscribe((response:any)=>{
      this.quantity=response
      console.log(response)
    },(error:HttpErrorResponse)=>{
      alert(error.error.errorMessage);
      this.errMsg=error.error.errorMessage;
      this.err=false;
      this.quantity=[]
    })
  }
  public getAmountSoldCategorywise(){
    this.service.getAmountSoldCategorywise().subscribe((response:any)=>{
      this.amountSold=response
    },(error:HttpErrorResponse)=>{
      alert(error.error.errorMessage);
      this.errMsg=error.error.errorMessage;
      this.err=false;
      this.amountSold=[]
    })
  }
}
