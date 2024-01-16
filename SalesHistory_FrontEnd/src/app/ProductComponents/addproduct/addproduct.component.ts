import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ProductService } from 'src/app/services/productservices.service';
import { Product } from '../product';


@Component({
  selector: 'app-addproduct',
  templateUrl: './addproduct.component.html',
  styleUrls: ['./addproduct.component.css']
})
export class AddproductComponent {
  err: boolean=true;
  errMsg:string='';

  constructor(private service:ProductService,private router:Router){

  }

  Prod:Product={
    prodId: 0,
    prodName: '',
    prodDesc: '',
    prodSubcategory: '',
    prodSubcategoryId: 0,
    prodSubcategoryDesc: '',
    prodCategory: '',
    prodCategoryId: 0,
    prodCategoryDesc: '',
    prodWeightClass: 0,
    prodUnitOfMeasure: '',
    prodPackSize: '',
    supplierId: 1,
    prodStatus: '',
    prodListPrice: 0,
    prodMinPrice: 0,
    prodTotal: '',
    prodTotalId: 0,
    prodSrcId: 0,
    prodEffFrom: new Date(),
    prodEffTo: new Date(),
    prodValid: ''
  }

  nameRegex=/^[A-Za-z]+$/
  weightRegex=/\d+\.{0,1}\d{1,3}kg$/gm


  //add logic
  public addProduct(){
    console.log("in addProduct");
    this.service.save(this.Prod).subscribe((response: any) => {
      this.err=true
      this.router.navigate(['/showproduct'])
  },(error:HttpErrorResponse)=>{
    alert(error.error.errorMessage);
    this.errMsg=error.error.errorMessage;
    this.err=false;
  });
  }

}
