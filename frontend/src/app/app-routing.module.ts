import { RouterModule, Routes } from '@angular/router';
import { UserListComponent } from './user/user-list/user-list.component';
import { UserFormComponent } from './user/user-form/user-form.component';
import { NgModule } from '@angular/core';

const routes: Routes = [
  { path: 'users',
    children: [
      {
        path: '',
        component: UserListComponent
      },
      {
        path: 'create',
        component: UserFormComponent
      },
      {
        path: ':id/edit',
        component: UserFormComponent
      }
    ]
  },
  { path: '', redirectTo: 'users', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
