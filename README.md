# Clinica-odontologica
MVC usando Spring Boot

# EndPoint: Paciente 
- Listar pacientes
GET: https://localhost:8081/pacientes/listar * sin body.
- Agregar paciente
POST: https://localhost:8081/pacientes/registrar
BODY:
  {
  "nombre": "nombre",
  "apellido": "apellido",
  "dni": 123,
  "fechaIngreso": "2023-11-22",
  "domicilioEntradaDto": {
  "calle": 11,
  "numero": 10,
  "localidad": "localidad",
  "provincia": "provincia"
  }
  }
- Buscar paciente por ID
GET: https://localhost:8081/pacientes/{id} * sin body.
- Editar paciente
PUT: https://localhost:8081/pacientes/actualizar 
BODY: 
  {
  "id": 3,
  "nombre": "Esmeralda",
  "apellido": "lidiosa",
  "dni": 122,
  "fechaIngreso": "2023-11-22",
  "domicilioEntradaDto": {
  "id": 4,
  "calle": "11",
  "numero": 10,
  "localidad": "usme",
  "provincia": "laredo"
  }
  }
- Eliminar paciente
DELETE: https://localhost:8081/pacientes/eliminar/{id} * sin body

# EndPoint: Odontologo

- Listar odontologos
  GET: https://localhost:8081/odontologos/listar * sin body.

- Agregar odontologos
POST:  https://localhost:8081/odontologos/registrar
BODY:
  {
  "matricula": "1234567894",
  "nombre": "raton",
  "apellido": "muelitas"
  }

- Buscar odontologos por ID 
GET: https://localhost:8081/odontologos/{id} * sin body.

- Editar odontologos 
PUT: https://localhost:8081/odontologos/actualizar 
BODY:{
  "id": 1,
  "matricula": "1234567894",
  "nombre": "roedor",
  "apellido": "maestro"
  }

- Eliminar odontologos 
DELETE: https://localhost:8081/odontologos/eliminar/{id} * sin body


# EndPoint: Turno
- Agregar turno 
POST: http://localhost:8081/turnos/registrar
BOBY: {
  "fechaYHora": "2023-11-25T22:30:00",
  "odontologo": {
  "id": 2
  /*"matricula": "1234567894",
  "nombre": "raton",
  "apellido": "muelitas"*/
  },
  "paciente": {
  "id": 3
  /*"nombre": "paco",
  "apellido": "jarras",
  "dni": 122,
  "fechaIngreso": "2023-11-22",
  "domicilioEntradaDto": {
  "calle": 11,
  "numero": 10,
  "localidad": "usme",
  "provincia": "laredo"
  }*/
  }
  }

- Listar turnos 
GET: http://localhost:8081/turnos/listar * sin body.
