import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ChannelService {

  constructor(private http:HttpClient) { }
  baseurl:string ="http://localhost:9090/api/v1";

  public getChannelUsedByCustomer(custId:number){
    return this.http.get(`${this.baseurl}/customers/channels/${custId}`)
  }

  public getChannelRevenue(channelId:number){
    return this.http.get(`${this.baseurl}/channels/${channelId}/revenue`)
  }

  public getChannelAggregate(channelId:number){
    return this.http.get(`${this.baseurl}/channels/${channelId}/aggregatesale`)
  }
}
