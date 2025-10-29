import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  templateUrl: './app.component.html',  // ✅ external HTML file
  imports: [RouterOutlet, RouterLink, RouterLinkActive],
  styles: [`.active { font-weight: bold; text-decoration: underline; }`]
})
export class AppComponent { }
