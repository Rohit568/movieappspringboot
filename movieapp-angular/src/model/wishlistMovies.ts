export class WishListMovies {

     id!: number;
     rankGiven!: string;
     movieId !: string;
     username!: string;
     titleName !: string;
     description!: string;
     imageName!: string;
     bigImageName!: string;
     ratingGiven!: string;
     dateAdded!: Date;
     yearAdded!: Date;

    constructor(
        id: number,
        rankGiven: string,
        movieId: string,
        username: string,
        titleName: string,
        description: string,
        imageName: string,
        bigImageName: string,
        ratingGiven: string,
        dateAdded: Date,
        yearAdded: Date

    ) {

        this.id = id;
        this.rankGiven= rankGiven
        this.movieId=movieId
        this.username=username
        this.titleName= titleName
        this.description= description
        this.imageName= imageName
        this.bigImageName=bigImageName
        this.ratingGiven= ratingGiven
        this.dateAdded= dateAdded
        this.yearAdded= yearAdded
    }
}