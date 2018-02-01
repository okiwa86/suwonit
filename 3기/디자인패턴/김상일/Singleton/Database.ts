export abstract class Database {
    private userPass: string;
    private userID: string;
    private conn: string;

    constructor(id: string, pass: string){
        this.userID = id;
        this.userPass = pass;
        this.conn = this.getConn(this.userID, this.userPass);
    }

    private getConn(id, pass): string {
        return id;
    };

    public write(): void {
        console.log(`[Used ${this.conn}] write!!`);
    }

    public modify(): void{
        console.log(`[Used ${this.conn}] modify!!`);
    }
}