import { ViewAllZahtevSertifikatComponent } from './SERTIFIKAT/view-all-zahtev-sertifikat/view-all-zahtev-sertifikat.component';
import { PovlacenjeSertifikatComponent } from './SERTIFIKAT/povlacenje-sertifikat/povlacenje-sertifikat.component';
import { ViewSertifikatComponent } from './SERTIFIKAT/view-sertifikat/view-sertifikat.component';
import { LoginComponent } from './login/login.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RoleGuard } from './SECURITY/role.service';
import { LoginGuard } from './SECURITY/login.service';
import { CreateSertifikatComponent } from './SERTIFIKAT/create-sertifikat/create-sertifikat.component';
import { ViewAllSertifikatComponent } from './SERTIFIKAT/view-all-sertifikat/view-all-sertifikat.component';

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
    canActivate:[LoginGuard]
  },
  {
    path: 'createSertifikat',
    component: CreateSertifikatComponent,
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
    path: 'viewSertifikat/:id',
    component: ViewSertifikatComponent,
    canActivate: [RoleGuard],
    data: {expectedRoles: 'ROLE_ADMIN'}
  },
  {
    path: 'povlacenjeSertifikat/:id',
    component: PovlacenjeSertifikatComponent,
    canActivate: [RoleGuard],
    data: {expectedRoles: 'ROLE_ADMIN'}
  },
  {
    path: 'viewAllZahtevSertifikat',
    component: ViewAllZahtevSertifikatComponent,
    canActivate: [RoleGuard],
    data: {expectedRoles: 'ROLE_ADMIN'}
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
