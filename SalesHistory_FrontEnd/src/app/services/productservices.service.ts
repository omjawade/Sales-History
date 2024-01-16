import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from '../ProductComponents/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private rootUrl: string = "http://localhost:9090/api/v1/products";

  constructor(private http: HttpClient) { }

  public findAll = (): any => {
    return this.http.get<Product[]>(`${this.rootUrl}`);
  }
  // public save=(data:any)=>{
  //   return this.http.post(`${this.rootUrl}`,data)
  //  }

  public save = (product: Product) => {
    console.log(product); 
    return this.http.post(`${this.rootUrl}`, product);
  }
  public getById = (prodId: number) => {
    console.log("in getbyId");
    return this.http.get<Product>(`${this.rootUrl}/${prodId}`);
  }
  public edit(data: Product) {
    return this.http.put<Product>(`${this.rootUrl}`, data)
  }

  public getByCategory(searchQuery:string){
    return this.http.get<Product[]>(`${this.rootUrl}/productscategory/${searchQuery}`);
  }

  public getByStatus(searchQuery:string){
    return this.http.get<Product[]>(`${this.rootUrl}/productsstatus/${searchQuery}`);
  }

  public getBySubcategory(searchQuery:string){
    return this.http.get<Product[]>(`${this.rootUrl}/productssubcategory/${searchQuery}`);
  }

  public getBySupplier(searchQuery:string){
    return this.http.get<Product[]>(`${this.rootUrl}/productssupplier/${searchQuery}`);
  }

  public getDuplicate(){
    return this.http.get<Product[]>(`${this.rootUrl}/duplicates`);
  }

  public getSoldProducts(){
    return this.http.get<Product[]>(`${this.rootUrl}/status/sold`)
  }

  public sortBy(sortParam:string){
    return this.http.get<Product[]>(`${this.rootUrl}/sort?field=${sortParam}`)
  }

  public getByChannel(channelName:string){
    return this.http.get(`${this.rootUrl}/soldbychannel?field=${channelName}`)
  }

  public getProductsSoldToCustomer(custId:number){
    return this.http.get(`http://localhost:9090/api/v1/customers/soldproducts/${custId}`)
  }

  public getProductsSoldByChannel(searchParam:string){
    return this.http.get(`http://localhost:9090/api/v1/channels/${searchParam}/mostSoldProducts`)
  }
}
