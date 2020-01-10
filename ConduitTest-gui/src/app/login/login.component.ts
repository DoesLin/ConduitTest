import { Component, OnInit } from '@angular/core';
import * as Chart from 'chart.js';

declare var $: any

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
    constructor() { }

    ngOnInit() {
      this.jquery_code();
    }
  
    jquery_code() {
    }
}