export interface JobSeekerDTO {
    id: number;
    name: string;
    email: string;
    phone: string;
    gender: string;
    address: string;
    dateOfBirth: string;  // Use string because JSON serializes Date as string
    photo: string;
}
