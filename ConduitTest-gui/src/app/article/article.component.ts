import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { HttpClientService } from '../service/httpclient.service';


export interface Food {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-article',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.scss'],
})
export class ArticleComponent implements OnInit {
  disableSelect = new FormControl(false);

  constructor(
    private httpClientService: HttpClientService) { }

  ngOnInit() {
    this.jquery_code();
  }

  jquery_code() {
  }


}
