class JsonParser
  class << self
    attr_accessor :file_data

    def parse_file(file_name)
      return @file_data if @file_data

      raise 'file name not exists' unless file_name

      file_path = "#{Rails.root}/app/assets/#{file_name}"
      @file_data = JSON.parse(File.read(file_path))
    end
  end
end
