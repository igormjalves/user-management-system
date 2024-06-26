export interface User {
  id?: number;
  name: string;
  email: string;
  department: Department;
}

export interface Department {
  id: number,
  name: string
}
