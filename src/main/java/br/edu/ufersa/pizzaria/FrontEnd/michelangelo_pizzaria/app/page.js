import Table from './UI/Table.js';

export default function Home() {
  const agendaSemanal = [
    {
      dia: 'Segunda-Feira',
      disciplina: 'Programação Web',
      horario: '08:00 às 11:30'
    },
    {
      dia: 'Terça-Feira',
      disciplina: 'Banco de Dados',
      horario: '08:00 às 11:30'
    },
    {
      dia: 'Quarta-Feira',
      disciplina: 'Programação Web',
      horario: '08:00 às 11:30'
    },
    {
      dia: 'Quinta-Feira',
      disciplina: 'Banco de Dados',
      horario: '08:00 às 11:30'
    },
    {
      dia: 'Sexta-Feira',
      disciplina: 'Programação Web',
      horario: '08:00 às 11:30'
    }
  ]
  return (
    <div>
      <h1 className="Cleiton">Horários</h1>
      <Table dados={agendaSemanal}/>
    </div>
  );
}