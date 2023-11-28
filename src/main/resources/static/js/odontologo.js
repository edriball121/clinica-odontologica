async function obtenerOdontologos() {
    try {
        const apiUrl = 'http://localhost:8081/odontologos/listar';
        const response = await fetch(apiUrl);

        if (!response.ok) {
            throw new Error(`Error al obtener odontologos: ${response.status}`);
        }

        const odontologos = await response.json();
        console.log('Odontologos obtenidos:', odontologos);

        const listaOdontologos = document.getElementById('listaOdontologos');
        // Limpiar la lista antes de agregar nuevos elementos
        listaOdontologos.innerHTML = '';

        odontologos.forEach(odontologo => {
            const elementoLista = document.createElement('li');
            elementoLista.classList.add('list-group-item');
            elementoLista.textContent = `${odontologo.nombre} ${odontologo.apellido}`;

            // Crear un botón de borrar con el evento onclick que llama a la función borrarPaciente
            const botonBorrar = document.createElement('button');
            botonBorrar.className = 'btn btn-danger';
            botonBorrar.innerText = 'Borrar';
            botonBorrar.onclick = function() {
                borrarOdontologo(odontologo.id);
            };

            const botonEditar = document.createElement('button');
            botonEditar.className ='btn btn-primary';
            botonEditar.innerText = 'Editar';
            botonEditar.onclick = function (){
                seleccionarOdontologo(odontologo.id);
            }


            elementoLista.appendChild(botonEditar);

            // Agregar el botón de borrar al elemento de la lista
            elementoLista.appendChild(botonBorrar);

            // Agregar el elemento de la lista al contenedor de la lista
            listaOdontologos.appendChild(elementoLista);
        });
    } catch (error) {
        console.error('Error:', error);
    }
}

async function registrarOdontologo() {
    try {
        const nombre = document.getElementById('nombre').value;
        const apellido = document.getElementById('apellido').value;
        const matricula = document.getElementById('matricula').value;

        const odontologo = {
            matricula: matricula,
            nombre: nombre,
            apellido: apellido

        };
        console.log(JSON.stringify(odontologo))

// Realizar la solicitud fetch con el objeto paciente convertido a JSON
        fetch('http://localhost:8081/odontologos/registrar', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(odontologo)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Error al registrar usuario: ${response.status}`);
                }
                return response.json();
            })
            .then(resultado => {
                console.log('Odontologo registrado:', resultado);
                // Limpiar los campos del formulario después de un registro exitoso
                document.getElementById('id').value = '';
                document.getElementById('nombre').value = '';
                document.getElementById('apellido').value = '';
                document.getElementById('matricula').value = '';
                obtenerOdontologos();
            })
            .catch(error => {
                console.error('Error al registrar usuario:', error);
            });
    } catch (error) {
        console.error('Error:', error);
    }
}

async function seleccionarOdontologo(id){
    const apiUrl = `http://localhost:8081/odontologos/${id}`;
    const response = await fetch(apiUrl, { method: 'GET' });
    const odontologo = await response.json();

    document.getElementById('id').value = odontologo.id;
    document.getElementById('nombre').value = odontologo.nombre;
    document.getElementById('apellido').value = odontologo.apellido;
    document.getElementById('matricula').value = odontologo.matricula;
    console.log(odontologo);
}

async function actualizarOdontologo(id){
    try {
        const id = document.getElementById('id').value;
        const nombre = document.getElementById('nombre').value;
        const apellido = document.getElementById('apellido').value;
        const matricula = document.getElementById('matricula').value;

        const odontologo = {
            id: id,
            nombre: nombre,
            apellido: apellido,
            dni: matricula

        };
        console.log(JSON.stringify(odontologo))

        fetch('http://localhost:8081/odontologos/actualizar', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(odontologo)
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
                document.getElementById('matricula').value = '';

                obtenerOdontologos();
            })
            .catch(error => {
                console.error('Error al registrar usuario:', error);
            });
    } catch (error) {
        console.error('Error:', error);
    }
}
async function borrarOdontologo(id) {
    try {
        const apiUrl = `http://localhost:8081/odontologos/eliminar/${id}`;
        const response = await fetch(apiUrl, { method: 'DELETE' });

        if (!response.ok) {
            throw new Error(`Error al borrar odontologo: ${response.status}`);
        }
        // Actualizar la lista después de borrar un paciente
        obtenerOdontologos();
    } catch (error) {
        console.error('Error al borrar odontologo:', error);
    }
}
window.onload = obtenerOdontologos();