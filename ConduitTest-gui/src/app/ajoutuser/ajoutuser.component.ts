import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';


export interface Food {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-ajoutuser',
  templateUrl: './ajoutuser.component.html',
  styleUrls: ['./ajoutuser.component.scss'],
  // providers: [NgbDropdownConfig] 
})
export class AjoutuserComponent  {
  // gradeListOfOption: { name: string; code: string; }[];

  // foods: Food[] = [
  //   {value: 'steak-0', viewValue: 'Steak'},
  //   {value: 'pizza-1', viewValue: 'Pizza'},
  //   {value: 'tacos-2', viewValue: 'Tacos'}
  // ];
  disableSelect = new FormControl(false);

   constructor() { }
    // constructor(config: NgbDropdownConfig) {
    //   // customize default values of dropdowns used by this component tree
    //   config.placement = 'down-left';
    //   config.autoClose = false;
    // }



    ngOnInit() {
      this.jquery_code();
    }
  
    jquery_code() {
    }
  
  
}
