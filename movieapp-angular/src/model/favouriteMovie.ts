export class FavouriteMovie{
 id!: number;
  ranking!: number;
  title! : string;
 image! : string;
  rating !: string;
  year! : number;

constructor(id:number, ranking:number, title: string, image : string,rating : string, year : number)
     {
        this.id = id;
        this.ranking = ranking;
        this.title =title;
        this.image = image;
        this.rating = rating;
        this.year = year;
     }
 
}