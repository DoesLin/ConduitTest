import { Component, OnInit } from '@angular/core';
import * as Chart from 'chart.js';

declare var $: any

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  title = 'Monitoring M2M';

  // projects = ['CVP', 'RCS', 'CHP', 'SIM²'];
  canvasCVP: any;
  canvasRCS: any;
  canvasCHP: any;
  canvasSIM: any;

  ctxCVP: any;
  ctxRCS: any;
  ctxCHP: any;
  ctxSIM: any;

  ngAfterViewInit() {
    this.canvasCVP = document.getElementById('chartCVP');
    this.canvasRCS = document.getElementById('chartRCS');
    this.canvasCHP = document.getElementById('chartCHP');
    this.canvasSIM = document.getElementById('chartSIM');

    this.ctxCVP = this.canvasCVP.getContext('2d');
    this.ctxRCS = this.canvasRCS.getContext('2d');
    this.ctxCHP = this.canvasCHP.getContext('2d');
    this.ctxSIM = this.canvasSIM.getContext('2d');

    let chartCVP = new Chart(this.ctxCVP, {
      type: 'pie',
      data: {
        labels: ["Running", "Stoped"],
        datasets: [{
          label: '# of Votes',
          data: [3, 1],
          backgroundColor: [
            'rgba(54, 162, 235, 1)',
            'rgba(255, 99, 132, 1)'
          ],
          borderWidth: 1
        }]
      },
      options: {
        responsive: false,
        display: true
      }
    });

    let chartRCS = new Chart(this.ctxRCS, {
      type: 'pie',
      data: {
        labels: ["Running", "Stoped"],
        datasets: [{
          label: '# of Votes',
          data: [3, 1],
          backgroundColor: [
            'rgba(54, 162, 235, 1)',
            'rgba(255, 99, 132, 1)'
          ],
          borderWidth: 1
        }]
      },
      options: {
        responsive: false,
        display: true
      }
    });

    let chartCHP = new Chart(this.ctxCHP, {
      type: 'pie',
      data: {
        labels: ["Running", "Stoped"],
        datasets: [{
          label: '# of Votes',
          data: [3, 1],
          backgroundColor: [
            'rgba(54, 162, 235, 1)',
            'rgba(255, 99, 132, 1)'
          ],
          borderWidth: 1
        }]
      },
      options: {
        responsive: false,
        display: true
      }
    });

    let chartSIM = new Chart(this.ctxSIM, {
      type: 'pie',
      data: {
        labels: ["Running", "Stoped"],
        datasets: [{
          label: '# of Votes',
          data: [3, 1],
          backgroundColor: [
            'rgba(54, 162, 235, 1)',
            'rgba(255, 99, 132, 1)'
          ],
          borderWidth: 1
        }]
      },
      options: {
        responsive: false,
        display: true
      }
    });
  }

  constructor() { }

  ngOnInit() {
    this.jquery_code();
  }

  jquery_code() {
    $(document).ready(function () {
      $('.tabs').tabs();
    });
  }

}
