import { Component, OnInit } from '@angular/core';
import { HttpClientService, ChefMagasin } from '../service/httpclient.service';
import { Router } from '@angular/router';
import { ChefmagasinDataService } from '../service/transferdata/chefmagasin-data.service';

declare var $: any


@Component({
  selector: 'app-chefmagasins',
  templateUrl: './chefmagasins.component.html',
  styleUrls: ['./chefmagasins.component.scss']
})
export class ChefmagasinsComponent implements OnInit {

  chefMagasins: ChefMagasin[];
  chefMagasinModif = new ChefMagasin('', '', '');

  constructor(private router: Router,
    private httpClientService: HttpClientService,
    private chefMagasindata: ChefmagasinDataService) { }

  ngOnInit() {
    this.jquery_code();
    this.httpClientService.getChefMagasins().subscribe(
      response => this.handleSuccessfulResponse(response),
    );
    this.chefMagasindata.currentChefMagasin.subscribe(chefMagasinModif => this.chefMagasinModif = chefMagasinModif);

  }

  jquery_code() {

    $(function () {
      $("tbody tr").click(function () {
        $(this).addClass("s1").siblings().removeClass("s1");
        $(this).find(":radio").attr("checked", true);
      });
    });
  }

  handleSuccessfulResponse(response) {
    this.chefMagasins = response;
  }

  addChefMagasin(): void {
    this.router.navigate(['chefmagasin-ajout']);
  };

  modifyChefMagasin(chefMagasin: ChefMagasin): void {
    this.chefMagasindata.changeChefMagasin(chefMagasin)
    this.router.navigate(['chefmagasin-modif']);
  };

  deleteChefMagasin(chefMagasin: ChefMagasin): void {
    console.log(chefMagasin);
    this.httpClientService.deleteChefMagasin(chefMagasin)
      .subscribe(data => {
        this.chefMagasins = this.chefMagasins.filter(u => u !== chefMagasin);
      })
  };

}
