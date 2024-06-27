import { Component } from '@angular/core';
import { User } from '../../models/models';
import { UserService } from '../../services/user.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog } from '@angular/material/dialog';
import { UserFormComponent } from '../user-form/user-form.component';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrl: './user-list.component.scss'
})
export class UserListComponent {

  dataSource = new MatTableDataSource<User>();
  displayedColumns: string[] = ['id', 'name', 'email', 'department', 'actions'];

  constructor(private userService: UserService,
              private dialog: MatDialog,
              private snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(): void {
    this.userService.getAll().subscribe((users: User[]) => {
      this.dataSource.data = users;
    }, (error) => {
      this.showMessage('Erro ao carregar listagem.', true);
    });
  }

  addNewUser(): void {
    const addNewUserDialog = this.dialog.open(UserFormComponent, {
      width: '400px',
      data: { user: null }
    });

    addNewUserDialog.afterClosed().subscribe(result => {
      if (result) {
        this.showMessage('Usuário criado com sucesso.', false);
        this.loadUsers();
      }
    });
  }

  editUser(user: User): void {
    const editUserDialog = this.dialog.open(UserFormComponent, {
      width: '400px',
      data: { user: user }
    });

    editUserDialog.afterClosed().subscribe(result => {
      if (result) {
        this.showMessage('Usuário editado com sucesso.', false);
        this.loadUsers();
      }
    });
  }

  deleteUser(id: number | undefined): void {
    if(id) {
      this.userService.delete(id).subscribe(() => {
        this.loadUsers();
        this.showMessage('Usuário excluído com sucesso.', false);
      }, (error) => {
        this.showMessage('Erro ao excluir usuário. Tente novamente.', true);
      });
    }
  }

  private showMessage(message: string, isError: boolean): void {
    this.snackBar.open(message, 'Fechar', {
      duration: 3000,
      horizontalPosition: 'right',
      verticalPosition: 'top',
      panelClass: isError ? ['error-snackbar'] : ['success-snackbar']
    });
  }
}
