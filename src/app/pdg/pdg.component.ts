import { Component, OnInit } from '@angular/core';
import * as Chart from 'chart.js';

declare var $: any

@Component({
  selector: 'app-login',
  templateUrl: './pdg.component.html',
  styleUrls: ['./pdg.component.scss']
})
export class PdgComponent implements OnInit {
    constructor() { }

    ngOnInit() {
      this.jquery_code();
    }
  
    jquery_code() {
    }
}