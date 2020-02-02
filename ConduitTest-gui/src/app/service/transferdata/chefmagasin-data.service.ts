import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { ChefMagasin } from '../httpclient.service';

@Injectable({
  providedIn: 'root'
})
export class ChefmagasinDataService {

  private chefMagasinSource = new BehaviorSubject(new ChefMagasin('', '', ''));
  currentChefMagasin = this.chefMagasinSource.asObservable();

  constructor() { }

  changeChefMagasin(chefMagasin: ChefMagasin) {
    this.chefMagasinSource.next(chefMagasin)
  }
}
