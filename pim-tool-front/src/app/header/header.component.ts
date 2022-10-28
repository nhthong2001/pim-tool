import {Component, OnInit, ViewChild} from '@angular/core';
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  @ViewChild("en") enLink;
  @ViewChild("fr") frLink;

  constructor(public translate: TranslateService) {

  }

  switchLanguage(lang: string) {
    if (lang === 'en') {
      this.enLink.nativeElement.style.color = 'black';
      this.frLink.nativeElement.style.color = '#007bff';
    } else {
      this.frLink.nativeElement.style.color = 'black';
      this.enLink.nativeElement.style.color = '#007bff';
    }
    this.translate.use(lang);
  }

  ngOnInit(): void {
  }

}
