async function obtenerOdontologos() {
    try {
        const apiUrl = 'http://localhost:8081/odontologos/listar';
        const response = await fetch(apiUrl);

        if (!response.ok) {
            throw new Error(`Error al obtener odontologos: ${response.status}`);
        }

        const odontologos = await response.json();
        console.log('Odontologos obtenidos:', odontologos);

        const selectOdontologos = document.getElementById('selectOdontologos');
        // Limpiar el select antes de agregar nuevas opciones
        selectOdontologos.innerHTML = '';

        odontologos.forEach(odontologo => {
            const option = document.createElement('option');
            option.value = odontologo.id;
            option.text = `${odontologo.matricula} ${odontologo.nombre} ${odontologo.apellido}`;
            selectOdontologos.appendChild(option);
        });
    } catch (error) {
        console.error('Error:', error);
    }
}

async function obtenerPacientes() {
    try {
        const apiUrl = 'http://localhost:8081/pacientes/listar';
        const response = await fetch(apiUrl);

        if (!response.ok) {
            throw new Error(`Error al obtener pacientes: ${response.status}`);
        }

        const pacientes = await response.json();
        console.log('Pacientes obtenidos:', pacientes);

        const selectPacientes = document.getElementById('selectPacientes');
        // Limpiar el select antes de agregar nuevas opciones
        selectPacientes.innerHTML = '';

        pacientes.forEach(paciente => {
            const option = document.createElement('option');
            option.value = paciente.id;
            option.text = `${paciente.dni} ${paciente.nombre} ${paciente.apellido}`;
            selectPacientes.appendChild(option);
        });
    } catch (error) {
        console.error('Error:', error);
    }
}

async function cargarSelects() {
    await obtenerOdontologos();
    await obtenerPacientes();
}

document.addEventListener("DOMContentLoaded", function () {
    cargarSelects();

    const formularioTurno = document.getElementById("registrarTurno");

    formularioTurno.addEventListener("submit", function (event) {
        event.preventDefault(); // Evitar que el formulario se envíe de la manera tradicional

        // Obtener los valores seleccionados
        const fechaHoraInput = document.getElementById("fechaYHora");
        const fechaYHora = formatearFecha(fechaHoraInput.value);
        const odontologoId = document.getElementById("selectOdontologos").value;
        const pacienteId = document.getElementById("selectPacientes").value;

        // Crear el objeto para enviar al servidor
        const turno = {
            fechaYHora: fechaYHora,
            odontologo: {
                id: odontologoId
            },
            paciente: {
                id: pacienteId
            }
        };

        // Llamar a la función para enviar el turno al servidor
        registrarTurno(turno);
    });
});

async function registrarTurno(turno) {
    console.log(turno);
    try {
        const apiUrl = 'http://localhost:8081/turnos/registrar';
        const response = await fetch(apiUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(turno)
        });

        if (!response.ok) {
            throw new Error(`Error al registrar turno: ${response.status}`);
        }

        const resultado = await response.json();
        console.log('Turno registrado:', resultado);
        obtenerTurnos();
    } catch (error) {
        console.error('Error al registrar turno:', error);
    }
}

function formatearFecha(fechaHora) {
    // Crear un objeto Date a partir de la cadena de fecha y hora
    const fecha = new Date(fechaHora);

    // Obtener componentes de fecha y hora
    const year = fecha.getFullYear();
    const month = ("0" + (fecha.getMonth() + 1)).slice(-2);
    const day = ("0" + fecha.getDate()).slice(-2);
    const hours = ("0" + fecha.getHours()).slice(-2);
    const minutes = ("0" + fecha.getMinutes()).slice(-2);

    // Formatear la cadena en el formato deseado
    return `${year}-${month}-${day}T${hours}:${minutes}:00`;
}

// Obtener y mostrar la lista de turnos
async function obtenerTurnos() {
    try {
        const apiUrl = 'http://localhost:8081/turnos/listar';
        const response = await fetch(apiUrl);

        if (!response.ok) {
            throw new Error(`Error al obtener turnos: ${response.status}`);
        }

        const turnos = await response.json();
        console.log('Turnos obtenidos:', turnos);

        const listaTurnos = document.getElementById('listaTurnos');
        // Limpiar la lista antes de agregar nuevos elementos
        listaTurnos.innerHTML = '';

        turnos.forEach(turno => {
            const elementoTurno = document.createElement('div');
            elementoTurno.classList.add('card', 'mb-3');

            // Crear el contenido de la tarjeta
            const contenidoTarjeta = `
                <div class="card-body">
                    <h5 class="card-title">Turno #${turno.id}</h5>
                    <p class="card-text">Odontólogo: ${turno.odontologo.nombre} ${turno.odontologo.apellido}</p>
                    <p class="card-text">Paciente: ${turno.paciente.nombre} ${turno.paciente.apellido}</p>
                    <p class="card-footer bg-transparent border-success">Identificador turno: ${turno.id}</p>
                </div>
            `;

            elementoTurno.innerHTML = contenidoTarjeta;

            // Agregar la tarjeta al contenedor de la lista
            listaTurnos.appendChild(elementoTurno);
        });
    } catch (error) {
        console.error('Error:', error);
    }
}

window.onload = function () {
    cargarSelects();
    obtenerTurnos();
};
