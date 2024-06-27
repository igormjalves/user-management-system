import { UserService } from './../../services/user.service';
import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Department, User } from '../../models/models';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrl: './user-form.component.scss'
})
export class UserFormComponent {
  userForm: FormGroup;
  isEditMode: boolean;
  departments: Department[] = [];
  filteredDepartments: Department[] = [];
  isAddingNewDepartment: boolean = false;

  constructor(
    private dialogRef: MatDialogRef<UserFormComponent>,
    private formBuilder: FormBuilder,
    private userService: UserService,
    @Inject(MAT_DIALOG_DATA) public data: { user: User }
  ) {
    this.isEditMode = !!data?.user;

    this.userForm = this.formBuilder.group({
      name: [data?.user ? data.user.name : '', Validators.required],
      email: [data?.user ? data.user.email : '', [Validators.required, Validators.email]],
      department: [data?.user ? data.user.department.name : '', Validators.required]
    });

    this.userService.getAllDepartments().subscribe((response) => {
      this.departments = response;
    });
  }

  onSubmit(): void {
    if(this.userForm.valid) {
      const formData = this.userForm.value;
      const departmentExists = this.departments.filter(deparment => deparment.name === formData.department);
      console.log(formData);
      if(departmentExists.length > 0) {
        formData.department = departmentExists[0];
      } else {
        formData.department = { name: formData.department };
      }
      if(this.isEditMode) {
        if(this.data.user.id) {
          this.userService.update(this.data.user.id, formData).subscribe((response) => {
            this.dialogRef.close(response);
          }, (error) => {
            console.log('Error: ', error);
          });
        }
      } else {
        this.userService.create(formData).subscribe((response) => {
          this.dialogRef.close(response);
        }, (error) => {
          console.log('Error: ', error);
        });
      }

    }
  }

  onClose(): void {
    this.dialogRef.close();
  }

  get name() { return this.userForm.get('name'); }
  get email() { return this.userForm.get('email'); }
  get department() { return this.userForm.get('department'); }

  filterDepartments(event: any): void {
    const value = event.target.value;
    const filterValue = value.toLowerCase();
    this.filteredDepartments = this.departments.filter(department =>
      department.name.toLowerCase().includes(filterValue)
    );
  }

}
