import { Component, OnInit } from '@angular/core';
import * as Chart from 'chart.js';

declare var $: any

@Component({
  selector: 'app-modificationuser',
  templateUrl: './modificationuser.component.html',
  styleUrls: ['./modificationuser.component.scss']
})
export class ModificationuserComponent implements OnInit {
    constructor() { }

    ngOnInit() {
      this.jquery_code();
    }
  
    jquery_code() {
    }
}