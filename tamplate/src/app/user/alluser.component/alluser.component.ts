import { Component, OnInit } from '@angular/core';
import { User } from '../../model/user.model';
import { UserService } from '../../service/user.service';

@Component({
  selector: 'app-alluser.component',
  standalone: false,
  templateUrl: './alluser.component.html',
  styleUrl: './alluser.component.css'
})
export class AlluserComponent implements OnInit {

  users: User[] = [];

  constructor(
    private userService: UserService
  ) { }

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers() {

    this.userService.getAllEmp().subscribe({
      next: (res) => {
        this.users = res;

      },
      error: (err)=>{
        console.log(err);
      }

    });


  }



}
