import { useEffect, useState } from 'react';
import FullCalendar from '@fullcalendar/react';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import { type EventInput } from '@fullcalendar/core';
import { useApiFetch } from '../api';

interface Lesson {
  id: string;
  dateTime: string;
  duration: number;
  group?: { name: string };
}

export const TeacherCalendar = () => {
  const apiFetch = useApiFetch();
  const [events, setEvents] = useState<EventInput[]>([]);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    apiFetch('/api/teacher/lessons?teacherId=default')
      .then((r) => (r.ok ? r.json() : Promise.reject()))
      .then((data: Lesson[]) => {
        setEvents(
          data.map((l) => ({
            id: String(l.id),
            title: l.group?.name ?? 'Lesson',
            start: l.dateTime,
            end: new Date(new Date(l.dateTime).getTime() + l.duration * 60000).toISOString(),
          }))
        );
      })
      .catch(() => setError('Failed to load lessons'));
  }, [apiFetch]);

  if (error) return <div className="text-red-600">{error}</div>;

  return (
    <FullCalendar plugins={[dayGridPlugin, timeGridPlugin]} initialView="timeGridWeek" events={events} />
  );
};
