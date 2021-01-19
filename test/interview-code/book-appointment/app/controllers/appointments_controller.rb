class AppointmentsController < ApplicationController
  skip_before_action :verify_authenticity_token
  before_action :validate_params, only: [:index]

  def index
    selected_providers = AppointmentScheduler.search_for_provider(params['minScore'].to_i,
                                                                  params['specialty'].downcase,
                                                                  params['date'].to_i)
    providers_names = selected_providers.map { |provider| provider.name }
    providers_names.reverse!
    render json: providers_names.to_json
  end

  def create
    successfully_scheduled_appointment = AppointmentScheduler.set_appointment(params['name'].downcase, params['date'].to_i)

    if successfully_scheduled_appointment
      return render json: { message: 'appointment successfully scheduled' }, status: 200
    end

    render json: { error: 'provider doesnt have availability' }, status: 400
  end

  private

  def validate_params
    unless params['specialty'].present?
      return render json: { error: 'no specialty supplied' }, status: 400
    end

    unless Utils::String.is_number?(params['date']) && Utils::DateTime.is_valid_date?(params['date'])
      return render json: { error: 'wrong date format' }, status: 400
    end
  end
end
