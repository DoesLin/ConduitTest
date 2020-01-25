import { Component, OnInit } from '@angular/core';
import * as Chart from 'chart.js';

declare var $: any

@Component({
  selector: 'app-cdm',
  templateUrl: './cdm.component.html',
  styleUrls: ['./cdm.component.scss']
})
export class CdmComponent implements OnInit {
    constructor() { }

    ngOnInit() {
      this.jquery_code();
    }
  
    jquery_code() {
    }
}