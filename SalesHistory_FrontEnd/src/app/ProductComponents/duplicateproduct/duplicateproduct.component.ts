import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductService } from 'src/app/services/productservices.service';

@Component({
  selector: 'app-duplicateproduct',
  templateUrl: './duplicateproduct.component.html',
  styleUrls: ['./duplicateproduct.component.css']
})
export class DuplicateproductComponent implements OnInit {
  errMsg: string = '';
  err: boolean=true;
  ngOnInit(): void {
    this.service.getDuplicate().subscribe((response: any) => {
      this.products = response;
    },(error:HttpErrorResponse)=>{
      alert(error.error.errorMessage);
      this.errMsg=error.error.errorMessage;
      this.err=false;
    })
  }
  constructor(private service: ProductService, private router: Router) { }
  products: any[] = []
}
