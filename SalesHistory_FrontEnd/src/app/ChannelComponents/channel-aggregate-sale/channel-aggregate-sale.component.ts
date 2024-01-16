import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ChannelService } from 'src/app/services/channel.service';

@Component({
  selector: 'app-channel-aggregate-sale',
  templateUrl: './channel-aggregate-sale.component.html',
  styleUrls: ['./channel-aggregate-sale.component.css']
})
export class ChannelAggregateSaleComponent implements OnInit {
  errMsg: string='';
  err: boolean=true;
  constructor(private service:ChannelService){}
  ngOnInit(): void {
    this.search()
  }

  searchQuery:string = '1'  //Variable Declarations
  channelName!:string 
  aggr!:number


  //finding which channel is selected
  setChannelName(){
    if(this.searchQuery===String(1)){
      this.channelName = 'Wholesale'
    }else if(this.searchQuery===String(2)){
      this.channelName = 'Retail'
    } else if(this.searchQuery===String(3)){
      this.channelName = 'E Commerce'
    } else if(this.searchQuery===String(4)){
      this.channelName = 'Marketplace'
    }
  }

  //search function for hitting specific endpoint
  public search(){
    let channelId = Number(this.searchQuery)
    this.setChannelName()
    this.service.getChannelAggregate(channelId).subscribe((response:any)=>{
      this.err=true
      this.aggr=Number(response);
    },(error:HttpErrorResponse)=>{
      this.aggr=0
      this.errMsg= error.error.errorMessage;
      this.err=false
    })
  }
}
