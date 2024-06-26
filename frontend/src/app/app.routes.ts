import { Routes } from '@angular/router';
import { UserListComponent } from './user/user-list/user-list.component';
import { UserDetailComponent } from './user/user-detail/user-detail.component';
import { UserFormComponent } from './user/user-form/user-form.component';

export const routes: Routes = [
  { path: 'users',
    children: [
      {
        path: '',
        component: UserListComponent
      },
      {
        path: ':id',
        component: UserDetailComponent
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
