import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ChannelService } from 'src/app/services/channel.service';
import { Channel } from '../Channel';

@Component({
  selector: 'app-channeltable',
  templateUrl: './channeltable.component.html',
  styleUrls: ['./channeltable.component.css']
})
export class ChanneltableComponent implements OnInit{
  constructor(private channelService:ChannelService){}
  ngOnInit(): void {
    
  }
    //Variable Declarations
  searchParam:string='usedByCustomer'
  searchQuery:string=''
  channels:Channel[]=[]

  errMsg:string=''
  err:boolean=true;

  //searching logic
  search(){
    console.log(this.searchParam);
    
    switch (this.searchParam) {
      case 'usedByCustomer':
        let custId:number = Number(this.searchQuery)
        this.channelService.getChannelUsedByCustomer(custId).subscribe((response:any)=>{
          this.errMsg='';
          this.err=true;
          this.channels = response;
        },(error:HttpErrorResponse)=>{
          alert(error.error.errorMessage);
          this.errMsg=error.error.errorMessage;
          this.err=false;
          this.channels=[]
        }
        )
        break;
    
      default:
        break;
    }
  }
}
