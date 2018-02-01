export class Volt {

    private volts: number;

    constructor(v: number) {
        this.volts = v;
    }

    getVolts(): number {
        return this.volts;
    }

    setVolts(volts: number): void {
        this.volts = volts;
    }
}