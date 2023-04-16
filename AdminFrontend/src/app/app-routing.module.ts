import { CreateZahtevZaSertifikatComponent } from './ZAHTEV-ZA-SERTIFIKAT/create-zahtev-za-sertifikat/create-zahtev-za-sertifikat.component';
import { ViewAllZahtevSertifikatComponent } from './ZAHTEV-ZA-SERTIFIKAT/view-all-zahtev-sertifikat/view-all-zahtev-sertifikat.component';
import { PovlacenjeSertifikatComponent } from './POVUCENI-SERTIFIKATI/povlacenje-sertifikat/povlacenje-sertifikat.component';
import { ViewSertifikatComponent } from './SERTIFIKAT/view-sertifikat/view-sertifikat.component';
import { LoginComponent } from './login/login.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RoleGuard } from './SECURITY/role.service';
import { LoginGuard } from './SECURITY/login.service';
import { ViewAllSertifikatComponent } from './SERTIFIKAT/view-all-sertifikat/view-all-sertifikat.component';
import { ViewZahtevZaSertifikatComponent } from './ZAHTEV-ZA-SERTIFIKAT/view-zahtev-za-sertifikat/view-zahtev-za-sertifikat.component';
import { ViewAllPovuceniSertifikatiComponent } from './POVUCENI-SERTIFIKATI/view-all-povuceni-sertifikati/view-all-povuceni-sertifikati.component';
import { VerifikacijaZahtevaZaSertifikatComponent } from './SECURITY/verifikacija-zahteva-za-sertifikat/verifikacija-zahteva-za-sertifikat.component';

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
    canActivate:[LoginGuard]
  },
  {
    path: 'createZahtevZaSertifikat',
    component: CreateZahtevZaSertifikatComponent,
    canActivate: [RoleGuard],
    data: {expectedRoles: 'ROLE_ADMIN'}
  },
  {
    path: 'viewAllSertifikat',
    component: ViewAllSertifikatComponent,
    canActivate: [RoleGuard],
    data: {expectedRoles: 'ROLE_ADMIN'}
  },
  {
    path: 'viewSertifikat/:alias',
    component: ViewSertifikatComponent,
    canActivate: [RoleGuard],
    data: {expectedRoles: 'ROLE_ADMIN'}
  },
  {
    path: 'viewZahtevZaSertifikat/:id',
    component: ViewZahtevZaSertifikatComponent,
    canActivate: [RoleGuard],
    data: {expectedRoles: 'ROLE_ADMIN'}
  },
  {
    path: 'povlacenjeSertifikat/:alias',
    component: PovlacenjeSertifikatComponent,
    canActivate: [RoleGuard],
    data: {expectedRoles: 'ROLE_ADMIN'}
  },
  {
    path: 'povuceniSertifikati',
    component: ViewAllPovuceniSertifikatiComponent,
    canActivate: [RoleGuard],
    data: {expectedRoles: 'ROLE_ADMIN'}
  },
  {
    path: 'viewAllZahtevSertifikat',
    component: ViewAllZahtevSertifikatComponent,
    canActivate: [RoleGuard],
    data: {expectedRoles: 'ROLE_ADMIN'}
  },
  {
    path: 'potvrdaZahteva/:token',
    component: VerifikacijaZahtevaZaSertifikatComponent,
    canActivate: [RoleGuard],
    data: {expectedRoles: 'ROLE_ADMIN'}
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
