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

        $(function() {
             $("tbody tr").click(function() {
              $(this).addClass("s1").siblings().removeClass("s1");
              $(this).find(":radio").attr("checked", true);
             });
            });
    }

  //  public tableHighLight() {
  //     // 获取tr
  //     let trs = $(".cuttingTbody").find("tr");
  //     // 循环遍历
  //     for (let index = 0; index < trs.length; index++){
  //         // 设置点击事件
  //         trs[index].onclick = function () {
  //             for (let i = 0; i < trs.length; i++){
  //                 trs[i].className = "";
  //             }
  //             this.className = "hover";
  //         }
  //     }
  // }

  public currentCdm;

  public selectCdm(event: any, item: any) {
    this.currentCdm = item.Vendeur;
  }

  public tableHighLight() {
    $("table#aijquery").on("click",function(e){
      $(e.target).parent("tr").addClass("bg-primary").siblings("tr.bg-primary").removeClass("bg-primary");
    });
  }
}