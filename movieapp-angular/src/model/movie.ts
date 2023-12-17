export class Movie{
 id!: string;
  rank!: number;
  title! : string;
  description!: string;
 image! : string;
 big_image!:string;
  rating !: string;
  year! : number;

constructor(id:string, rank:number, title: string,description:string, image : string, big_image:string, rating : string, year : number)
     {
        this.id = id;
        this.rank = rank;
        this.title =title;
        this.description = description;
        this.image = image;
        this.big_image = big_image;
        this.rating = rating;
        this.year = year;
     }
 
}