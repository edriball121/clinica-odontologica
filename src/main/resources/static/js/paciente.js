async function obtenerPacientes() {
    try {
        const apiUrl = 'http://localhost:8081/pacientes/listar';
        const response = await fetch(apiUrl);

        if (!response.ok) {
            throw new Error(`Error al obtener pacientes: ${response.status}`);
        }

        const pacientes = await response.json();
        console.log('Pacientes obtenidos:', pacientes);

        const listaPacientes = document.getElementById('listaPacientes');
        // Limpiar la lista antes de agregar nuevos elementos
        listaPacientes.innerHTML = '';

        pacientes.forEach(paciente => {
            const elementoLista = document.createElement('li');
            elementoLista.classList.add('list-group-item');
            elementoLista.textContent = `${paciente.nombre} ${paciente.apellido}`;

            // Crear un botón de borrar con el evento onclick que llama a la función borrarPaciente
            const botonBorrar = document.createElement('button');
            botonBorrar.className = 'btn btn-danger';
            botonBorrar.innerText = 'Borrar';
            botonBorrar.onclick = function() {
                borrarPaciente(paciente.id);
            };

            const botonEditar = document.createElement('button');
            botonEditar.className ='btn btn-primary';
            botonEditar.innerText = 'Editar';
            botonEditar.onclick = function (){
                seleccionarPaciente(paciente.id);
            }


            elementoLista.appendChild(botonEditar);

            // Agregar el botón de borrar al elemento de la lista
            elementoLista.appendChild(botonBorrar);

            // Agregar el elemento de la lista al contenedor de la lista
            listaPacientes.appendChild(elementoLista);
        });
    } catch (error) {
        console.error('Error:', error);
    }
}
async function registrarUsuario() {
    try {
        const nombre = document.getElementById('nombre').value;
        const apellido = document.getElementById('apellido').value;
        const dni = document.getElementById('dni').value;
        const fechaIngreso = document.getElementById('fechaIngreso').value;

        const calle = document.getElementById('calle').value;
        const numero = document.getElementById('numero').value;
        const localidad = document.getElementById('localidad').value;
        const provincia = document.getElementById('provincia').value;

        const paciente = {
            nombre: nombre,
            apellido: apellido,
            dni: parseInt(dni),  // Convertir el valor de dni a un número entero
            fechaIngreso: fechaIngreso,
            domicilioEntradaDto: {
                calle: parseInt(calle),
                numero: parseInt(numero),
                localidad: localidad,
                provincia: provincia
            }
        };
        console.log(JSON.stringify(paciente))

// Realizar la solicitud fetch con el objeto paciente convertido a JSON
        fetch('http://localhost:8081/pacientes/registrar', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(paciente)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Error al registrar usuario: ${response.status}`);
                }
                return response.json();
            })
            .then(resultado => {
                console.log('Paciente registrado:', resultado);
                // Limpiar los campos del formulario después de un registro exitoso
                document.getElementById('id').value = '';
                document.getElementById('nombre').value = '';
                document.getElementById('apellido').value = '';
                document.getElementById('dni').value = '';
                document.getElementById('fechaIngreso').value = '';
                document.getElementById('idDomi').value = '';
                document.getElementById('calle').value = '';
                document.getElementById('numero').value = '';
                document.getElementById('localidad').value = '';
                document.getElementById('provincia').value = '';
                obtenerPacientes();
            })
            .catch(error => {
                console.error('Error al registrar usuario:', error);
            });
    } catch (error) {
        console.error('Error:', error);
    }
}

async function seleccionarPaciente(id){
    const apiUrl = `http://localhost:8081/pacientes/id/${id}`;
    const response = await fetch(apiUrl, { method: 'GET' });
    const paciente = await response.json();

    document.getElementById('id').value = paciente.id;
    document.getElementById('nombre').value = paciente.nombre;
    document.getElementById('apellido').value = paciente.apellido;
    document.getElementById('dni').value = paciente.dni;
    document.getElementById('fechaIngreso').value = paciente.fechaIngreso;
    document.getElementById('idDomi').value = paciente.domicilioSalidaDto.id;
    document.getElementById('calle').value = paciente.domicilioSalidaDto.calle;
    document.getElementById('localidad').value = paciente.domicilioSalidaDto.localidad;
    document.getElementById('provincia').value = paciente.domicilioSalidaDto.provincia;
    document.getElementById('numero').value = paciente.domicilioSalidaDto.numero;
    console.log(paciente);
}

async function actualizarUsuario(id){
    try {
        const id = document.getElementById('id').value;
        const nombre = document.getElementById('nombre').value;
        const apellido = document.getElementById('apellido').value;
        const dni = document.getElementById('dni').value;
        const fechaIngreso = document.getElementById('fechaIngreso').value;

        const idDomi = document.getElementById('idDomi').value;
        const calle = document.getElementById('calle').value;
        const numero = document.getElementById('numero').value;
        const localidad = document.getElementById('localidad').value;
        const provincia = document.getElementById('provincia').value;

        const paciente = {
            id: id,
            nombre: nombre,
            apellido: apellido,
            dni: parseInt(dni),  // Convertir el valor de dni a un número entero
            fechaIngreso: fechaIngreso,
            domicilioEntradaDto: {
                id: idDomi,
                calle: parseInt(calle),
                numero: parseInt(numero),
                localidad: localidad,
                provincia: provincia
            }
        };
        console.log(JSON.stringify(paciente))

        fetch('http://localhost:8081/pacientes/actualizar', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(paciente)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Error al registrar usuario: ${response.status}`);
                }
                return response.json();
            })
            .then(resultado => {
                console.log('Paciente registrado:', resultado);
                // Limpiar los campos del formulario después de un registro exitoso
                document.getElementById('id').value = '';
                document.getElementById('nombre').value = '';
                document.getElementById('apellido').value = '';
                document.getElementById('dni').value = '';
                document.getElementById('fechaIngreso').value = '';
                document.getElementById('idDomi').value = '';
                document.getElementById('calle').value = '';
                document.getElementById('numero').value = '';
                document.getElementById('localidad').value = '';
                document.getElementById('provincia').value = '';
                obtenerPacientes();
            })
            .catch(error => {
                console.error('Error al registrar usuario:', error);
            });
    } catch (error) {
        console.error('Error:', error);
    }
}
async function borrarPaciente(id) {
    try {
        const apiUrl = `http://localhost:8081/pacientes/eliminar/${id}`;
        const response = await fetch(apiUrl, { method: 'DELETE' });

        if (!response.ok) {
            throw new Error(`Error al borrar paciente: ${response.status}`);
        }
        // Actualizar la lista después de borrar un paciente
        obtenerPacientes();
    } catch (error) {
        console.error('Error al borrar paciente:', error);
    }
}
window.onload = obtenerPacientes;