import {Database} from "./Database";

export class DatabaseB extends Database{
    private static _instance: DatabaseB = null;
    protected constructor(){
        super('DatabaseB', '111');
    }
    public static getInstance(): DatabaseB{
        if(DatabaseB._instance == null){
            DatabaseB._instance = new DatabaseB();
        }
        return DatabaseB._instance;
    }
}