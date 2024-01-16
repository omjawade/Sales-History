import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerService } from 'src/app/services/customer.service';
import { Customer } from '../Customer';

@Component({
  selector: 'app-update-customer-credit-limit',
  templateUrl: './update-customer-credit-limit.component.html',
  styleUrls: ['./update-customer-credit-limit.component.css']
})
export class UpdateCustomerCreditLimitComponent implements OnInit{
  errMsg: string ='';
  err: boolean = true;
  ngOnInit(): void {
    this.route.paramMap.subscribe((param)=>{
      let custId=Number(param.get("custId"))
      console.log(custId);
      this.getById(custId)
    })
  }
  constructor(private custService:CustomerService, private route:ActivatedRoute, private router:Router){}

  creditLimit!: number;
  cust!:Customer;

  // featch customer id  
  getById(custId:number){
    console.log(custId);
    this.custService.getById(custId).subscribe((data: Customer)=>{
      this.cust =data;
      this.creditLimit=data.custCreditLimit;
    })
  }

  // update customer logic
  
  edit(){
    this.cust.custCreditLimit=this.creditLimit;
    this.custService.edit(this.cust).subscribe((response: any) => {
      this.err=true
      this.router.navigate(['/showcustomer'])
  },(error:HttpErrorResponse)=>{
    alert(error.error.errorMessage);
    this.errMsg=error.error.errorMessage;
    this.err=false;
  });
  }
}
