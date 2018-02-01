
import {Database} from "./Database";

export class DatabaseA extends Database{
    private static _instance: DatabaseA = null;
    protected constructor(){
        super('DatabaseA', '111');
    }
    public static getInstance(): DatabaseA{
        if(DatabaseA._instance == null){
            DatabaseA._instance = new DatabaseA();
        }
        return DatabaseA._instance;
    }
}