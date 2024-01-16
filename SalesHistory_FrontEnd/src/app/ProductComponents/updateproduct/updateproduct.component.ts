import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from '../product';
import { ProductService } from '../../services/productservices.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-updateproduct',
  templateUrl: './updateproduct.component.html',
  styleUrls: ['./updateproduct.component.css']
})
export class UpdateproductComponent implements OnInit {
  errMsg: string = '';
  err: boolean = true;

  constructor(private service: ProductService, private router: Router, private route: ActivatedRoute) {

  }
  ngOnInit(): void {
    this.route.paramMap.subscribe((param) => {
      let prodId = Number(param.get("prodId"))
      console.log(prodId);
      this.getById(prodId)
    })

  }

  prodForm: Product = {
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
    supplierId: 0,
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

  getById(id: number) {
    console.log(id);

    this.service.getById(id).subscribe((data: Product) => {
      console.log("before sub");
      this.prodForm = data;
    })
  }
  edit() {
    this.service.edit(this.prodForm).subscribe((response: any) => {
      this.err = true
      this.router.navigateByUrl('showproduct')
    }, (error: HttpErrorResponse) => {
      this.errMsg = error.error.errorMessage;
      this.err = false
      console.log(this.errMsg);
      alert(this.errMsg);
    })
  }
}
