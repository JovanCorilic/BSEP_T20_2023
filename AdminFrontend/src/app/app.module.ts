import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Interceptor } from './SECURITY/intercept.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NavbarComponent } from './navbar/navbar.component';
import { LoginComponent } from './login/login.component';
import { CreateSertifikatComponent } from './SERTIFIKAT/create-sertifikat/create-sertifikat.component';
import { ViewAllSertifikatComponent } from './SERTIFIKAT/view-all-sertifikat/view-all-sertifikat.component';
import { ViewSertifikatComponent } from './SERTIFIKAT/view-sertifikat/view-sertifikat.component';
import { ViewAllZahtevSertifikatComponent } from './SERTIFIKAT/view-all-zahtev-sertifikat/view-all-zahtev-sertifikat.component';
import { PovlacenjeSertifikatComponent } from './SERTIFIKAT/povlacenje-sertifikat/povlacenje-sertifikat.component';
import { CreateZahtevZaSertifikatComponent } from './SERTIFIKAT/create-zahtev-za-sertifikat/create-zahtev-za-sertifikat.component';



@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
    CreateSertifikatComponent,
    ViewAllSertifikatComponent,
    ViewSertifikatComponent,
    ViewAllZahtevSertifikatComponent,
    PovlacenjeSertifikatComponent,
    CreateZahtevZaSertifikatComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: Interceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
