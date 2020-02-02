import { Component, OnInit } from '@angular/core';
import { ChefMagasin, HttpClientService } from '../service/httpclient.service';
import { Router } from '@angular/router';
import { ChefmagasinDataService } from '../service/transferdata/chefmagasin-data.service';


@Component({
  selector: 'app-chefmagasin-modif',
  templateUrl: './chefmagasin-modif.component.html',
  styleUrls: ['./chefmagasin-modif.component.scss']
})
export class ChefmagasinModifComponent implements OnInit {

  chefMagasin: ChefMagasin = new ChefMagasin('', '', '');

  constructor(private router: Router,
    private httpClientService: HttpClientService,
    private chefMagasindata: ChefmagasinDataService) { }

  ngOnInit() {
    this.chefMagasindata.currentChefMagasin.subscribe(chefMagasin => this.chefMagasin = chefMagasin)
    console.log(this.chefMagasin);
  }

  modifyChefMagasin(): void {
    console.log(this.chefMagasin);
    if (this.chefMagasin.username == '' || this.chefMagasin.username == null) {
      alert("ChefMagasin username is empty.");
    } else if (this.chefMagasin.password == '' || this.chefMagasin.password == null) {
      alert("ChefMagasin password is empty.");
    } else {
      this.httpClientService.modifyChefMagasin(this.chefMagasin)
        .subscribe(
          data => {
            alert("ChefMagasin modified successfully.");
            this.router.navigate(['/chefmagasins'])
          },
          error => {
            var msg = error['error']['message']
            alert('Modification fail: ' + msg.split(";")[0])
          });
    }

  }

  annuler(): void {
    this.router.navigate(['/chefmagasins'])
  }

}
