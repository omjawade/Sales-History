import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SalesService } from 'src/app/services/sales.service';

@Component({
  selector: 'app-mostsoldproduct',
  templateUrl: './mostsoldproduct.component.html',
  styleUrls: ['./mostsoldproduct.component.css']
})
export class MostsoldproductComponent implements OnInit{
  errMsg: string = '';
  err: boolean=true;

  constructor(private service:SalesService,private router:Router ){}

  ngOnInit(): void {
    //getting most sold product
    this.service.getMostSoldProduct().subscribe((response:any)=>{
        this.sold=response
        this.err=true
    },(error:HttpErrorResponse)=>{
      alert(error.error.errorMessage);
      this.errMsg=error.error.errorMessage;
      this.err=false;
      this.sold=[]
    })
  }

  sold:any[] = []

}
