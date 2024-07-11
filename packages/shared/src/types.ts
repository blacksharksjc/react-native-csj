export type PrependIfDefined<T extends string, S extends string> = T extends "" ? T : `${S}${T}`;

export type Concat<T extends string[], S extends string = '.'> = T extends [
  infer F extends string,
  ...infer R extends string[]
] ? `${F}${PrependIfDefined<Concat<R, S>, S>}` : '';

export type Remap<T, U extends {[key in keyof T]: any}> = {
  [Property in keyof T]: U[Property];
}
