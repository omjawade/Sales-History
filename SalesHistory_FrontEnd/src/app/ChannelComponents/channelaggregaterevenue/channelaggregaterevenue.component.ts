import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ChannelService } from 'src/app/services/channel.service';

@Component({
  selector: 'app-channelaggregaterevenue',
  templateUrl: './channelaggregaterevenue.component.html',
  styleUrls: ['./channelaggregaterevenue.component.css']
})
export class ChannelaggregaterevenueComponent implements OnInit {

  constructor(private service: ChannelService) { }
  ngOnInit(): void {
    this.search()
  }

  //Variable Declarations
  searchQuery: string = '1'
  channelName!: string
  revenue!: number
  errMsg: string = ''
  err: boolean = true;

  //finding which channel is selected
  setChannelName() {
    if (this.searchQuery === String(1)) {
      this.channelName = 'Wholesale'
    } else if (this.searchQuery === String(2)) {
      this.channelName = 'Retail'
    } else if (this.searchQuery === String(3)) {
      this.channelName = 'E Commerce'
    } else if (this.searchQuery === String(4)) {
      this.channelName = 'Marketplace'
    }
  }

  //search function for hitting specific endpoint
  public search() {
    let channelId = Number(this.searchQuery)
    this.setChannelName()
    this.service.getChannelRevenue(channelId).subscribe((response: any) => {
      this.err = true
      this.revenue = Number(response);
    }, (error: HttpErrorResponse) => {
      this.revenue = 0
      this.errMsg = error.error.errorMessage;
      this.err = false
    })
  }
}
